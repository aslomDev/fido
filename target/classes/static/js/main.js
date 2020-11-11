$(document).ready(function () {

    $("#hide").click(function () {
        $("#box").show(500, function () {
            $("#box").delay(2000).hide(500);
        });
    });
    $('.btn-primary').click(function () {


        $('.card').slideDown(1000,function () {
            var w = 0;
            var progress = setInterval(function () {

                w += 0.5;
                $("#progress").css("width",w+"%");
                if(w == 100){
                    clearInterval(progress);
                    w = 0;
                    $("#progress").css("width",w+"0%");
                }

            },10);
            $(".card").delay(2000).slideUp(1000);
        })

    })
    $('#toggle').click(function () {
        $('#box').fadeToggle(500);
    });
    var index = 0;

    var articleArray [
        {
            title: "directoryweb"
            text: "directoryweb\n" +
            "Скрипт инвестиционного проекта Gelevian » Каталог скриптов. Скрипты бесплатно. Создание сайтов. Оптимизация.\n" +
            "\n" +
            "Скрипт инвестиционного проекта Gelevian. Поддержка Payeer, Perfect Money,QIWI. Скрипт инвестиционного проекта Gelevian. Поддержка Payeer, Perfect Money,QIWI.\n" +
            "Посещено\n" +
            "red4u\n" +
            "Скрипт хайпа Migomoney » Red4U - Все для вебмастера! Скрипты и создание сайтов!\n" +
            "\n" +
            "анный скрипт работает на платежной системе кивасе, PerfectMoney, Yandex, Payeer. Так же есть webmoney но отключен можно подключить) Красивый дизайн (Работу выполнил DesignerGamora)\n" +
            "Посещено\n" +
            "yadi\n" +
            "Скрипт хайпа 2x24hours.zip — Яндекс.Диск\n" +
            "\n" +
            "Посмотреть и скачать с Яндекс.Диска\n" +
            "Посещено"
        },
        {
            title: "osdn\n" +
            "Downloading File /JumpStart + WinPcap.rar - Dumpper v.60.3 - OSDN"
            text: "osdn\n" +
            "Downloading File /JumpStart + WinPcap.rar - Dumpper v.60.3 - OSDN\n" +
            "\n" +
            "Free download page for Project Dumpper v.60.3's JumpStart + WinPcap.rar.Dumpper es un software portable y gratuito enfocado a la gestion de redes wireless en Windows. Ademas, incorpora varios m...\n" +
            "Посещено"
        }
        {
            title: "stackoverflow\n" +
            "How to change value of object which is inside an array using JavaScript or"
        }
    ];
    $('#Add').click(function () {


if(index < article.length){
    var article = '<div class="card-body">' +
        '<p>' <h1>'+articleArray[index].title+'</h1>'+
        <p>'+articleArray[index].text+'</p>'+
    '</div>';
    $('.card').before(article);
    index++;
}else{
    $('.card-header').after("<h6 class='text-center text-danger'>Maqola qolmadi");
}

    })

});
var  message = "salom dunyo";

setTimeout(function () {
    console.log(message);
},2000);

var sekund = 0;

var getMessage = setInterval(function () {

    sekund++;
    if(sekund <= 10){
        clearInterval(getMessage);
    }

    console.log(sekund);
},1000);