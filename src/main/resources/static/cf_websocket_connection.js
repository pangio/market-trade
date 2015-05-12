var stompClient = null;

// Web-socket is connected when the browser visit the MarketTrade web app
// The charts are drawn with initial data broadcasted from the server.
function connect() {
	var socket = new SockJS('/charts');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);		
		stompClient.subscribe("/app/init", function(stats) {
			drawBuyChart(JSON.parse(stats.body));
			drawSellChart(JSON.parse(stats.body));
			drawPieChart(JSON.parse(stats.body));
        });
		
		stompClient.subscribe('/topic/charts', function(stats) {
			drawBuyChart(JSON.parse(stats.body));
			drawSellChart(JSON.parse(stats.body));
			drawPieChart(JSON.parse(stats.body));
		});
	});
}
// Web-socket is disconnected when the page gets closed
function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}