# 📍region-service

대한민국 행정구역 데이터를 관리하는 서비스입니다.  
Spring Boot 기반의 REST API와, Python 기반의 데이터 초기화 도구를 포함합니다.

## 🧩 구성

- `src/`: Spring Boot 기반 지역 API 및 내부 로직
- `scripts/`: Geo 데이터(SHP 등)를 PostgreSQL(PostGIS)에 저장하는 Python 스크립트

---

## 🐍 Python SHP → PostGIS 변환 도구

`scripts/converter.py`는 다음을 수행합니다:
- SHP 파일을 읽어 EPSG:4326으로 변환
- 시도 / 시군구 / 읍면동별 코드 및 계층구조 저장
- geometry는 `region_geometries`, 메타데이터는 `regions`에 분리 저장

### 🐍 Python 버전
- Python **3.13.3** 에서 테스트되었습니다.

### 🔧 실행 방법
#### 1. 의존성 설치

```bash
pip install -r requirements.txt

```
#### 2. 환경 변수 설정

.env 파일 생성
```
DB_URL=postgresql://<user>:<password>@localhost:5432/<database>
LEVEL1_PATH=/N3A_G0010000/N3A_G0010000.shp
LEVEL2_PATH=/N3A_G0100000/N3A_G0100000.shp
LEVEL3_PATH=/LSMD_ADM_SECT_UMD_서울/LSMD_ADM_SECT_UMD_11_202505.shp
```

#### 3. 데이터 파일 준비

브이월드: https://www.vworld.kr/v4po_main.do

행정구역 shape 파일들을 다운로드한 후 압축 해제:
- 1뎁스 (시도): N3A_G0010000.shp
- 2뎁스 (시군구): N3A_G0100000.shp
- 3뎁스 (읍면동, 서울만 예시): LSMD_ADM_SECT_UMD_11_202505.shp

#### 4. 스크립트 실행
```bash
python converter.py
```

### 📊 데이터베이스 구조
| 테이블명           | 설명                                                    |
|--------------------|---------------------------------------------------------|
| `regions`          | 행정구역 기본정보 (`code`, `name`, `depth`, `parent_code`) |
| `region_geometries`| 행정구역의 `geometry` 컬럼만 따로 저장                  |

```sql
CREATE TABLE regions (
    code TEXT PRIMARY KEY,
    name TEXT,
    parent_code TEXT,
    depth INT
);

CREATE TABLE region_geometries (
    code TEXT PRIMARY KEY,
    geometry GEOMETRY(MultiPolygon, 4326)
);
```

--- 

## Spring Boot API (추가 예정)
향후 REST API를 통해 지역 목록, 검색, 필터링 기능 제공 예정입니다.