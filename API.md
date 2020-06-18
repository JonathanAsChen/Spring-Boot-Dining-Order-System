# API

###List of Products

```
GET /sell/buyer/product/list
```

Params

```
None
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": [
        {
            "name": "hot",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "test porridge",
                    "price": 1.2,
                    "description": "test description",
                    "icon": "http://test.com",
                }
            ]
        },
        {
            "name": "test 2",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "test 2",
                    "price": 10.9,
                    "description": "test description 2",
                    "icon": "http://test.com",
                }
            ]
        }
    ]
}
```


###Create Order

```
POST /sell/buyer/order/create
```

Params

```
name: "test name"
phone: "18868822111"
address: "test addr"
openid: "ew3euwhd7sjw9diwkq" //buyer wechat openid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //QTY
}]

```

Return

```
{
  "code": 0,
  "msg": "success",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

###List of Orders

```
GET /sell/buyer/order/list
```

Params

```
openid: 18eu2jwk2kse3r42e2e
page: 0 //start from page 0
size: 10
```

Return

```
{
  "code": 0,
  "msg": "success",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "test name",
      "buyerPhone": "18868877111",
      "buyerAddress": "test addr",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "test name",
      "buyerPhone": "18868877111",
      "buyerAddress": "test addr",
      "buyerOpenid": "18eu2jwk2kse3r42e2e",
      "orderAmount": 0,
      "orderStatus": 0,
      "payStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

###Get order detail

```
GET /sell/buyer/order/detail
```

Params

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "test name",
          "buyerPhone": "18868877111",
          "buyerAddress": "test addr",
          "buyerOpenid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "payStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "test 2",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
        ]
    }
}
```

###Cancel order

```
POST /sell/buyer/order/cancel
```

Params

```
openid: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

Return

```
{
    "code": 0,
    "msg": "success",
    "data": null
}
```

###get openid

```
redirect : /sell/wechat/authorize
```

params:

```
returnUrl: http://xxx.com/abc  //【required】
```

return:

```
http://xxx.com/abc?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
```


