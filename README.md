# BACK-END DEVELOPER | CHALLENGE
Gerçekleşen satışın kargo durumunu sorgulayabileceğimiz restful servis geliştirmenizi bekliyoruz.

## GENEL BİLGİ
* Mikro servis kullanılan bir şirkette çalıştığınızı düşünün.
* Product, Sale, Shipping uygulamaları bulunuyor.
* Bu uygulamalardan ihtiyaç duyulan verileri toplayarak ilgili
servisi geliştirmeniz bekleniyor.
* Java (Spring Boot) veya .net core kullanmalısınız.

## MİKRO SERVİSLER
1. Satış (Sale) 
http://5e209e06e31c6e0014c60962.mockapi.io/api/sales/3
Bu servisten satışa ait ürün bilgisini alabilirsiniz.
2. Ürün (Product)
http://5e209e06e31c6e0014c60962.mockapi.io/api/products/47
Satış uygulamasından aldığınız "productId" ile bu servisi çağırabilir,
ürün detaylarına ulaşabilirsiniz.
3. Kargo (Shipping)
http://5e209e06e31c6e0014c60962.mockapi.io/api/shipping/3
Bu servisten ilgili satışın kargo durumunu öğrenebilirsiniz.
Status: false (Ürün kargoda, teslim edilmedi)
Status: true (Ürün alıcıya teslim edildi)

* İkinci sayfada örnek request / response bulunmaktadır.

## BONUS PUANLAR:
* Unit Test yazmak
* Integration Test yazmak
* Swagger ile dokümante etmek
* Dockerize etmek

__ÖRNEK:__

Request:

```
/sale/{saleId}/shipping
```

Response:

```json
{
    "status": "TESLİM EDİLDİ",
    "sale": {
        "id": 3,
        "code": "bf610641-da64-4153-ad34-0aadf7a993e1"
    },
    "product": {
        "id": 1,
        "name": "Tasty Frozen Keyboard",
        "price": 150.00
    } 
}
```


Dockerize:
```
docker build -t cargo-server .
docker run --name=cargo-server --publish=8888:8080 cargo-server
```
