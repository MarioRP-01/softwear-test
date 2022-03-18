function loadCharts(){
    $.ajax({
        url: "/apiadmin/statics",
        type: "GET"
    }).done(function(response) {
        console.log(response)
        let label = []
        let dataEarns = []
        let dataSales = []
        let backgroundColor = []
        response.forEach(product =>{
            console.log(product)
            label.push(product.productName)
            dataEarns.push(product.earns)
            dataSales.push(product.sales)
            backgroundColor.push(randomRGB())
        })
        initCharts(label, dataEarns, dataSales, backgroundColor)
    })
}

const randomNum = () => Math.floor(Math.random() * (235 - 52 + 1) + 52);

const randomRGB = () => `rgb(${randomNum()}, ${randomNum()}, ${randomNum()})`;

function initCharts(label, dataEarns, dataSales, backgroundColor){
    var ctx_earning = document.getElementById('Earnings');

    var Earnings = new Chart(ctx_earning, {
        type: 'bar',
        data: {
            labels: label,
            datasets: [{
                data: dataEarns,
                backgroundColor: backgroundColor,
                borderColor: 'rgba(43, 6, 23)',
                borderWidth: 2,
                label: 'Amount',
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: "Earnings chart",
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    var ctx_sales = document.getElementById('Sales');

    var Sales = new Chart(ctx_sales, {
        type: 'bar',
        data: {
            labels: label,
            datasets: [{
                data: dataSales,
                backgroundColor: backgroundColor,
                borderColor: 'rgba(43, 6, 23)',
                borderWidth: 2,
                label: 'Amount',
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: "Sales chart",
            },
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}

$(document).ready(function() {
    loadCharts();
});