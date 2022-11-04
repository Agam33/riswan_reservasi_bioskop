# Reservasi Bioskop #

## Daftar isi ##
1. [Database Schema Challenge 7](#Database Schema Challenge 5)
2. [Response](#Response)
3. [Tugas Pada Challenge 7](#Tugas pada Challenge 7)

## Push Notification ##
<img height="500" src="https://user-images.githubusercontent.com/59663235/199898126-3477c0c2-1451-4bc2-b15c-f4df30aabd9d.gif">

## Database Schema Challenge 7 ##
<hr>
<img src="https://user-images.githubusercontent.com/59663235/193450349-86ca1d5e-5415-4703-819f-4db47d2d760b.png" alt="ERD-ch4"/>
<br>
<ul>
</ul>
Schema akan diupdate sesuai dengan kebutuhan.
<hr>

<p> 
    Swagger : <a>http://localhost:8080/swagger-ui/index.html</a>
</p>

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

## Tugas pada Challenge 7 ##
<ol>
    <li>Mengimplementasikan Clean Code</li>
    <li>Mengimplementasikan Unit Testing</li>
    <li>Membuat push notification (Firebase)</li>
</ol>