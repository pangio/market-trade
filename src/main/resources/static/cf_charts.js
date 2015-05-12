google.load("visualization", "1", {
	packages : [ "corechart" ]
});

// Buy/Sell Comparison Chart
function drawPieChart(stats) {
	var data = google.visualization.arrayToDataTable([
			[ 'Currency', 'Buy', 'Sell' ],
			[ 'EUR', stats.eurBuy, stats.eurSell ],
			[ 'USD', stats.usdBuy, stats.usdSell ],
			[ 'AUD', stats.audBuy, stats.audSell ] ]);
	var options = {
		title : 'Buy/Sell Comparison Chart',
		vAxis : { title : "Trades Quantity" },
		hAxis : { title : "Currency" },
		seriesType : "bars",
		series : { 3 : { type : "line" } }
	};
	var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
	chart.draw(data, options);
}

// Currency Comparison - BUY
function drawBuyChart(stats) {
	var data = google.visualization.arrayToDataTable([ 
            [ 'BUY', 'Total Buy' ],
			[ 'EUR', stats.eurBuy ], 
			[ 'USD', stats.usdBuy ],
			[ 'AUD', stats.audBuy ] ]);
	var options = { title : 'Currency Comparison - BUY' };
	var chart = new google.visualization.PieChart(document.getElementById('piechart_buy'));
	chart.draw(data, options);
}

// Currency Comparison - SELL
function drawSellChart(stats) {
	var data = google.visualization.arrayToDataTable([
			[ 'Sell', 'Total Sell' ], 
			[ 'EUR', stats.eurSell ],
			[ 'USD', stats.usdSell ], 
			[ 'AUD', stats.audSell ] ]);
	var options = { title : 'Currency Comparison - SELL' };
	var chart = new google.visualization.PieChart(document.getElementById('piechart_sell'));
	chart.draw(data, options);
}
