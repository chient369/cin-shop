
$(document).ready(function() {


	const userShopWs = " https://25de-116-82-246-181.ngrok.io/cinshop/ws/cinshop"
	var socket = new SockJS(userShopWs);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/admin', function(resp) {
			var data = JSON.parse(resp.body)
			renderNotify(data);
		});
	});

	function renderNotify(data) {
		var orderURL = "/shopAdmin/order/" + data.info.orderId;
		var msg = data.info.msg

		$('#notify-box').prepend(
			`
		<a href="${orderURL}" class="dropdown-item">
		<h6 class="fw-normal mb-0 py-3">
						<i class="fa fa-brands fa-shopify text-success fa-2x"></i>
						<span>${msg}</span>
						</h6>
					<hr class="dropdown-divider">
					</a>
		`
		)
		$(".toast-body").text(msg)
		$(".toast").toast('show');
	}

});