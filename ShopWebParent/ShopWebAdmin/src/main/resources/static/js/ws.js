
$(document).ready(function() {
	//window.localStorage.clear()
	var notifies = [];
	var messages = [];
	renderNotifies();






	const webUserPath = $("#webUserPath").val();

	const userShopWs = webUserPath + "/ws/cinshop"
	var socket = new SockJS(userShopWs);
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/admin', function(resp) {
			var data = JSON.parse(resp.body)
			updateStorage(data)
			renderNotifies();
			showToast(data);
			if (data.type == "CONTACT") {
				createNewContactEle(data);
			}
		});
	});

	function showToast(data) {
		var msg = data.info.msg
		$(".toast-body").text(msg)
		$(".toast").toast('show');
	}

	function updateStorage(data) {
		console.log("type :" +data.type)
		if (data.type == "CONTACT") {
			messages.push(data);
			var msgStorage = window.localStorage.getItem("message");
			if (JSON.parse(msgStorage) == null) {
				window.localStorage.setItem("message", JSON.stringify(msgStorage));
			} else {
				var msgArr = JSON.parse(msgStorage);
				notifyArr.push(data)
				window.localStorage.setItem("message", JSON.stringify(msgArr));
			}
		}
		else {

			notifies.push(data)
			var notifyStorage = window.localStorage.getItem("notify");
			if (JSON.parse(notifyStorage) == null) {
				window.localStorage.setItem("notify", JSON.stringify(notifies));
			} else {
				var notifyArr = JSON.parse(notifyStorage);
				notifyArr.push(data)
				window.localStorage.setItem("notify", JSON.stringify(notifyArr));
			}
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
	if (notifies == null) return;
	var countNotify = notifies.length > 0 ? notifies.length : 0;
	$(".notify-num").text(countNotify);

	notifies.forEach((item, index) => {
		var notify = item;

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
function createNewContactEle(data) {
	var ctx = data.info.contact;
	var email = ctx.email;
	var shortName = email.slice(0, email.indexOf("@"));
	var shortContent = ctx.content.slice(0, 25) + "...";
	var contactId = "msg-" + ctx.id;
	$('#msg-nav-box').prepend(
		`<a href="#" class="dropdown-item">
						<div class="d-flex align-items-center">
							<div class="ms-2">
								<h6 class="fw-normal mb-0">${shortName}からメッセージが受信しました</h6>
							</div>
						</div>
					</a>
					<hr class="dropdown-divider">
						
		`
	)
	$('#msg-box').prepend(
		`
			<div class="d-flex align-items-center border-bottom py-3">
								<i class="fa fa-duotone fa-user fa-2x"></i>
								<div class="w-100 ms-3">
									<div class="d-flex w-100 justify-content-between">
										<h6 class="mb-0">${shortName}</h6>
									</div>														
										<span>${shortContent}</span>
								</div>
								<button type="button" class="btn btn-secondary" data-bs-toggle="tooltip" title="返信" 　
									onclick="reply(${ctx.id})"><i
										class="fa fa-light fa-share-from-square"></i></button>
							</div>
						
		`
	)
}
