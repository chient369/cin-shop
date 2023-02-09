
$(document).ready(function() {
	//window.localStorage.clear()
	var notifies = [];
	renderNotifies();






	const webUserPath = $("#webUserPath").val();

	const userShopWs = webUserPath + "/ws/cinshop"
	var socket = new SockJS(userShopWs);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/admin', function(resp) {
			var data = JSON.parse(resp.body)
			updateNotifyStorage(resp.body)
			renderNotifies();
			showToast(data);
		});
	});

	function showToast(data) {
		var msg = data.info.msg
		$(".toast-body").text(msg)
		$(".toast").toast('show');
	}

	function updateNotifyStorage(data) {
		notifies.push(data)
		var notifyStorage = window.localStorage.getItem("notify");
		console.log("response data :" + window.localStorage.getItem("notify"))

		if (JSON.parse(notifyStorage) == null) {
			window.localStorage.setItem("notify", JSON.stringify(notifies));
		} else {
			var notifyArr = JSON.parse(notifyStorage);
			notifyArr.push(data)
			window.localStorage.setItem("notify", JSON.stringify(notifyArr));
		}
	}


});
function removeNotify(id) {
	var notifyStorage = window.localStorage.getItem("notify");
	var notifies = JSON.parse(notifyStorage);
	var filtered = notifies.filter(function(value, index, arr) {
		return index != id;
	});

	var countNotify = filtered.length > 0 ? filtered.length : 0;
	$(".notify-num").text(countNotify);


	window.localStorage.setItem("notify", JSON.stringify(filtered))
	renderNotifies();
}

function renderNotifies() {
	$('#notify-box').empty();

	var notifyStorage = window.localStorage.getItem("notify");
	var notifies = JSON.parse(notifyStorage);
	var countNotify = notifies.length > 0 ? notifies.length : 0;
	$(".notify-num").text(countNotify);
	if (notifies == null) return;

	notifies.forEach((item, index) => {
		var notify = JSON.parse(item);

		if (notify.type == "ORDER") {
			createNewOrderOfNotify(notify, index);
		}
		if (notify.type == "USER") {
			createNewUserOfNotify(notify, index);
		}
	})
}
function createNewOrderOfNotify(notify, index) {
	var orderURL = "/shopAdmin/order/" + notify.info.orderId;
	var msg = notify.info.msg;
	$('#notify-box').prepend(
		`
			<div class="d-flex">
		<a href="${orderURL}" class="dropdown-item notify-ele" onclick="disable(this)">
						<h6 class="fw-normal mb-0 py-1">
							<i class="fa fa-brands fa-shopify text-success fa-2x"></i>
							<span>${msg}</span>
						</h6>
					</a>
							<button class="btn btn-link remove-notify" onclick="removeNotify(${index})"><i class="fa fa-light fa-xmark"></i></button>
						</div>
						<hr class="dropdown-divider">
						
		`
	)
}
function createNewUserOfNotify(notify, index) {
	var msg = notify.info.msg;
	$('#notify-box').prepend(
		`
			<div class="d-flex">
		<a href="#" class="dropdown-item notify-ele" onclick="disable(this)" >
						<h6 class="fw-normal mb-0 py-1">
							<i class="fa fa-solid fa-user-plus fa-2x"></i>
							<span>${msg}</span>
						</h6>
					</a>
							<button class="btn btn-link remove-notify" onclick="removeNotify(${index})"><i class="fa fa-light fa-xmark"></i></button>
						</div>
						<hr class="dropdown-divider">
						
		`
	)
}