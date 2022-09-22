# Reservasi Bioskop

<h3>Database Schema - Challenge 4</h3>
<hr>
<img src="https://user-images.githubusercontent.com/59663235/191777997-1c538aa0-d2c6-4bc8-8bfa-8c94105afabe.png" alt="ERD-ch4"/>
<br>
<p>Berikut table yang digunakan untuk keperluan challenge 4: </p>
<ul>
    <li><b>film</b>: film table di isi lewat <i>/api/v1/films/addAll atau /api/v1/films/add</i></li>
    <li><b>film_genre</b>: dibuat menggunakan anotasi @JoinTable</li>
    <li><b>genre</b>: table genre di isi secara otomatis ketika aplikasi dijalankan. 
    <br> Pada file <i>genre-data.json</i> data-data table diinput dan dimasukan ke database
    menggunakan <b>Jackson2RepositoryPopulatorFactoryBean</b>.</li>
    <li><b>seat</b>: sama dengan genre, table seat di isi ketika aplikasi dijalankan.</li>
    <li><b>schedule</b></li>
    <li><b>users</b></li>
    <li><b>studio</b></li>
    <li><b>seat_studio</b></li>
</ul>
table selain daftar diatas hanya pemanis.
<hr>
<h5>Response</h5>

```
{
    "statusCode": 200,
    "timeStamp": "2022-09-23 00:00:00",
    "message": "success",
    "data": [
        {
            "filmCode": "film-001",
            "title": "Pengabdi Mantan 1",
            ...
        },
        {
            "filmCode": "film-002",
            "title": "Pengabdi Mantan 2",
            ...
        },
        {
            "filmCode": "film-003",
            "title": "Pengabdi Mantan 3",
            ...
        }
    ]
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





