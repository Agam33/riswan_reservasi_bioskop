# Reservasi Bioskop #

## Daftar isi ##
1. [Database Schema Challenge 4](#Database-Schema-Challenge-4)
2. [Response](#Response)
3. [Business Logic Pada Challenge 4](#Business-Logic-Pada-Challenge-4)


## Database Schema Challenge 4 ##
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
    <li><b>schedule</b>: di isi melalui <i>/api/v1/films/addSchedule</i></li>
    <li><b>users</b>: di isi melalui <i>/api/v1/user/add</i></li>
    <li><b>studio</b>: belum dipakai pada challenge ini.</li>
    <li><b>seat_studio</b>: dibuat menggunakan anotasi @JoinTable</li>
</ul>
table selain daftar diatas hanya pemanis. Schema akan diupdate sesuai dengan kebutuhan.
<hr>

## Response ##
<hr>
<h5> Response Success </h5>

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
```
{
    "statusCode": 200,
    "timeStamp": "2022-09-23 00:00:00",
    "message": "success",
    "data": {
        "filmCode": "film-001",
        "title": "Pengabdi Mantan 1",
        ...
    }
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
## Business Logic pada Challenge 4 ##
1. Menambahkan film baru:<br><br>
<i>/api/v1/films/add</i>
```
{
    "title": "Luck (2022)",
    "overview": "Tiba-tiba menemukan dirinya di Tanah Keberuntungan yang belum pernah dilihat sebelumnya, orang paling sial di dunia harus bersatu dengan makhluk ajaib di sana untuk membalikkan peruntungannya",
    "runtime": 110,
    "releaseDate": "2022-09-09",
    "onShow": true,
    "genre": 2
}
```
<i>/api/v1/films/addAll</i>
```
[
    {
        "title": "Fall (2022)",
        "overview": "Untuk sahabat Becky dan Hunter, hidup adalah tentang menaklukkan ketakutan dan mendorong batas. Tetapi setelah mereka mendaki 2.000 kaki ke puncak menara radio terpencil yang ditinggalkan, mereka menemukan diri mereka terdampar tanpa jalan turun. Sekarang keterampilan panjat ahli Becky dan Hunter akan diuji saat mereka mati-matian berjuang untuk bertahan hidup dari unsur-unsur, kurangnya persediaan, dan ketinggian yang menyebabkan vertigo.",
        "runtime": 110,
        "releaseDate": "2022-09-09",
        "onShow": true,
        "genre": 2
    },
    {
        "title": "Jurassic World Dominion (2022)",
        "overview": "Four years after Isla Nublar was destroyed, dinosaurs now live—and hunt—alongside humans all over the world. This fragile balance will reshape the future and determine, once and for all, whether human beings are to remain the apex predators on a planet they now share with history’s most fearsome creatures.",
        "runtime": 150,
        "releaseDate": "2022-09-09",
        "onShow": true,
        "genre": 2
    },
    ...
]
```
2. Mengupdate nama Film: <br><br>
<i>/api/v1/films/update</i>
```
{
    "filmCode" : "film-7313",
    "newName": "{newName}"
}
```
3. Menghapus film: <br><br>
<i>/api/v1/films/delete?id={filmId}</i><br><br>

4. Menampilkan film yang sedang tayang: <br><br>
<i>/api/v1/films/nowPlaying</i><br><br>

5. Menampilkan jadwal dari film tertentu: <br><br>
<i>/api/v1/films/detail/schedule?id={filmId}</i><br><br>
```
{
    "statusCode": 202,
    "timeStamp": "2022-09-23 08:57:14",
    "message": "success",
    "data": {
        "filmId": "film-7313",
        "title": "Fall (2022)",
        "schedules": [
            {
                "startTime": "14:00:00",
                "endTime": "16:16:00",
                "showAt": "2022-09-23",
                "price": 50000.00
            },
            {
                "startTime": "20:00:00",
                "endTime": "22:16:00",
                "showAt": "2022-09-23",
                "price": 50000.00
            }
        ]
    }
}
```

7. Menambahkan user: <br><br>
<i>/api/v1/user/add</i><br><br>

8. Mengupdate user: <br><br>
<i>/api/v1/user/update</i>
```
{
    "email" : "riswan@gmail.com",
    "username" : "riswan223"
}
```
8. Menghapus user: <br><br>
<i>/api/v1/user/delete?email={email}</i>

----
Tambahan : 

* Menambahkan schedule ke film tertentu: <br><br>
<i>/api/v1/films/addSchedule</i>
```
{
    "filmId": "{filmId}",
    "startTime": "{HH:mm}",
    "endTime": "{HH:mm}",
    "showAt": "{yyyy-MM-dd}",
    "price": 0
}
```
* Mengambil semua film <br><br>
<i>/api/v1/films</i>
```
{
    "statusCode": 202,
    "timeStamp": "2022-09-23 09:12:10",
    "message": "success",
    "data": [
        {
            "filmCode": "film-7313",
            "title": "Fall (2022)",
            "overview": "Untuk sahabat Becky dan Hunter, hidup adalah tentang menaklukkan ketakutan dan mendorong batas. Tetapi setelah mereka mendaki 2.000 kaki ke puncak menara radio terpencil yang ditinggalkan, mereka menemukan diri mereka terdampar tanpa jalan turun. Sekarang keterampilan panjat ahli Becky dan Hunter akan diuji saat mereka mati-matian berjuang untuk bertahan hidup dari unsur-unsur, kurangnya persediaan, dan ketinggian yang menyebabkan vertigo.",
            "runtime": 110,
            "releaseDate": "2022-09-09T00:00:00.000+00:00",
            "onShow": true,
            "genres": [
                {
                    "id": 2,
                    "genre": "ADVENTURE"
                }
            ]
        },
        {
            "filmCode": "film-5805",
            "title": "The Black Phone (2022)",
            "overview": "Finney Blake, a shy but clever 13-year-old boy, is abducted by a sadistic killer and trapped in a soundproof basement where screaming is of little use. When a disconnected phone on the wall begins to ring, Finney discovers that he can hear the voices of the killer’s previous victims. And they are dead set on making sure that what happened to them doesn’t happen to Finney.",
            "runtime": 110,
            "releaseDate": "2022-09-09T00:00:00.000+00:00",
            "onShow": false,
            "genres": [
                {
                    "id": 3,
                    "genre": "CRIME"
                }
            ]
        },
        ...
    ]
}
```





