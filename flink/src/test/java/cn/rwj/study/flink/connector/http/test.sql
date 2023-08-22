DROP TABLE IF EXISTS cust_http_source;
CREATE TABLE cust_http_source(
    id string,
    name string,
    sex string
) WITH (
    'connector' = 'http',
    'http.url' = 'http://192.168.66.100:8888',
    'http.interval' = '1000',
    'format' = 'csv'
);

SELECT id, name, sex FROM cust_http_source;