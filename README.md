# Reservasi Bioskop #

## Daftar isi ##
1. [Database Schema Challenge 5](#Database-Schema-Challenge-4)
2. [Response](#Response)
3. [Business Logic Pada Challenge 5](#Business-Logic-Pada-Challenge-4)

## Database Schema Challenge 5 ##
<hr>
<img src="https://user-images.githubusercontent.com/59663235/193450349-86ca1d5e-5415-4703-819f-4db47d2d760b.png" alt="ERD-ch4"/>
<br>
<ul>
</ul>
Schema akan diupdate sesuai dengan kebutuhan.
<hr>

## Response ##
<hr>
<h5> Response Success </h5>

```
{
    "statusCode": 200,
    "timeStamp": "2022-09-23 00:00:00",
    "message": "success",
    "data": []
}
```
```
{
    "statusCode": 200,
    "timeStamp": "2022-09-23 00:00:00",
    "message": "success",
    "data": {}
}
```

<hr>
<h5> Response Error </h5>

```
{
    "statusCode": 404,
    "timeStamp": "2022-09-22 15:00:15",
    "message": "film tidak ada"
}
```