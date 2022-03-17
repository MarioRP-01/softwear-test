
var ctx_earning = document.getElementById('Earnings');

var Earnings = new Chart(ctx_earning, {
    type: 'bar',
    data: {
        labels: [],
        datasets: [{
            data: [],
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1,
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

function loadCharts(){
    $.ajax({
        url: "/apiadmin/statics",
        type: "GET",
        _csrf: token
    }).done(function(response) {
        
        console.log('products loaded: ' + JSON.stringify(response.data));
        response.data.forEach(static=> {
            Earnings.data.labels.push(static.productName);
            Earnings.data.datasets[0].data.push(static.earns);
        })
        Earnings.update();
    })
}

const Sales = new Chart(document.getElementById('Sales'), {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

$(document).ready(function() {
    loadCharts();
});