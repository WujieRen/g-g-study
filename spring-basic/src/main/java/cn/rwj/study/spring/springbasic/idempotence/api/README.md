# 思路
用 redis 缓存唯一id（save），然后在调用接口时，将 id 带上，接口调用成功后删除唯一id。

# 测试方式
1. 先调用接口 /idGenerator/getIdGeneratorToken 获取 uuid
2. 将获取到的 uuid 作为 body 参数的一部分，调用接口 /order/save-order ，参数示例：
```json
{
    "header": {
        "token": "2bab80c0-ed00-4fc5-b6f0-e4d0401b0d9e"
    }
}
```
重复调用返回结果：重复请求