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