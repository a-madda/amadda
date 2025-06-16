import os
import geopandas as gpd
from sqlalchemy import create_engine, text
from shapely.geometry import MultiPolygon, Polygon
from dotenv import load_dotenv

# .env 로드
load_dotenv()

DB_URL = os.getenv("DB_URL")
LEVEL1_PATH = os.getenv("LEVEL1_PATH")
LEVEL2_PATH = os.getenv("LEVEL2_PATH")
LEVEL3_PATH = os.getenv("LEVEL3_PATH")

def ensure_multipolygon(geom):
    if isinstance(geom, Polygon):
        return MultiPolygon([geom])
    return geom

def save_regions(gdf, depth):
    engine = create_engine(DB_URL)

    # 좌표계 변환
    if gdf.crs and gdf.crs.to_epsg() != 4326:
        gdf = gdf.to_crs(epsg=4326)

    gdf['code'] = gdf['code'].astype(str)

    # parent_code 설정
    if depth == 1:
        gdf['code'] = gdf['code'].str[:2]
        gdf['parent_code'] = None
    elif depth == 2:
        gdf['code'] = gdf['code'].str[:5]
        gdf['parent_code'] = gdf['code'].str[:2]
    elif depth == 3:
        gdf['code'] = gdf['code'].str[:10]
        gdf['parent_code'] = gdf['code'].str[:5]
    else:
        raise ValueError(f"Unsupported depth: {depth}")

    gdf['depth'] = depth
    gdf['geometry'] = gdf['geometry'].apply(ensure_multipolygon)

    # 기존 데이터 필터링
    with engine.connect() as conn:
        result = conn.execute(text("SELECT code FROM regions"))
        existing_codes = {row[0] for row in result}

    gdf = gdf[~gdf['code'].isin(existing_codes)]

    attr_df = gdf.drop(columns=["geometry"])
    geom_df = gdf[["code", "geometry"]]

    attr_df.to_sql("regions", con=engine, if_exists="append", index=False)
    geom_df.to_postgis("region_geometries", con=engine, if_exists="append", index=False)

    print(f"{depth}뎁스 {len(attr_df)}건 저장 완료 (geometry 분리 저장)")

def main():
    # 1뎁스: 시도
    gdf1 = gpd.read_file(LEVEL1_PATH, encoding='euc-kr')
    gdf1 = gdf1.rename(columns={"BJCD": "code", "NAME": "name"})
    gdf1 = gdf1[["code", "name", "geometry"]]
    save_regions(gdf1, depth=1)

    # 2뎁스: 시군구 (서울, 경기)
    gdf2 = gpd.read_file(LEVEL2_PATH, encoding='euc-kr')
    gdf2 = gdf2.rename(columns={"BJCD": "code", "NAME": "name"})
    gdf2 = gdf2[["code", "name", "geometry"]]
    gdf2['code'] = gdf2['code'].astype(str)
    gdf2 = gdf2[gdf2['code'].str.startswith(('11', '41'))]
    save_regions(gdf2, depth=2)

    # 3뎁스: 읍면동 (서울만)
    gdf3 = gpd.read_file(LEVEL3_PATH, encoding='euc-kr')
    gdf3 = gdf3.rename(columns={"EMD_CD": "code", "EMD_NM": "name"})
    gdf3 = gdf3[["code", "name", "geometry"]]
    gdf3['code'] = gdf3['code'].astype(str)
    save_regions(gdf3, depth=3)

if __name__ == "__main__":
    main()