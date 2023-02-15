(function($) {
	"use strict";

	// Spinner
	var spinner = function() {
		setTimeout(function() {
			if ($('#spinner').length > 0) {
				$('#spinner').removeClass('show');
			}
		}, 1);
	};
	spinner();


	// Back to top button
	$(window).scroll(function() {
		if ($(this).scrollTop() > 300) {
			$('.back-to-top').fadeIn('slow');
		} else {
			$('.back-to-top').fadeOut('slow');
		}
	});
	$('.back-to-top').click(function() {
		$('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
		return false;
	});


	// Sidebar Toggler
	$('.sidebar-toggler').click(function() {
		$('.sidebar, .content').toggleClass("open");
		return false;
	});


	// Progress Bar
	$('.pg-bar').waypoint(function() {
		$('.progress .progress-bar').each(function() {
			$(this).css("width", $(this).attr("aria-valuenow") + '%');
		});
	}, { offset: '80%' });


	// Calender
	$('#calender').datetimepicker({
		inline: true,
		format: 'L'
	});


	// Testimonials carousel
	$(".testimonial-carousel").owlCarousel({
		autoplay: true,
		smartSpeed: 1000,
		items: 1,
		dots: true,
		loop: true,
		nav: false
	});


	// Worldwide Sales Chart
	var ctx1 = $("#worldwide-sales").get(0).getContext("2d");
	var myChart1 = new Chart(ctx1, {
		type: "bar",
		data: {
			labels: ["2016", "2017", "2018", "2019", "2020", "2021", "2022"],
			datasets: [{
				label: "USA",
				data: [15, 30, 55, 65, 60, 80, 95],
				backgroundColor: "rgba(0, 156, 255, .7)"
			},
			{
				label: "UK",
				data: [8, 35, 40, 60, 70, 55, 75],
				backgroundColor: "rgba(0, 156, 255, .5)"
			},
			{
				label: "AU",
				data: [12, 25, 45, 55, 65, 70, 60],
				backgroundColor: "rgba(0, 156, 255, .3)"
			}
			]
		},
		options: {
			responsive: true
		}
	});


	// Salse & Revenue Chart
	var ctx2 = $("#salse-revenue").get(0).getContext("2d");
	var myChart2 = new Chart(ctx2, {
		type: "line",
		data: {
			labels: ["2016", "2017", "2018", "2019", "2020", "2021", "2022"],
			datasets: [{
				label: "Salse",
				data: [15, 30, 55, 45, 70, 65, 85],
				backgroundColor: "rgba(0, 156, 255, .5)",
				fill: true
			},
			{
				label: "Revenue",
				data: [99, 135, 170, 130, 190, 180, 270],
				backgroundColor: "rgba(0, 156, 255, .3)",
				fill: true
			}
			]
		},
		options: {
			responsive: true
		}
	});



	// Single Line Chart
	var ctx3 = $("#line-chart").get(0).getContext("2d");
	var myChart3 = new Chart(ctx3, {
		type: "line",
		data: {
			labels: [50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150],
			datasets: [{
				label: "Salse",
				fill: false,
				backgroundColor: "rgba(0, 156, 255, .3)",
				data: [7, 8, 8, 9, 9, 9, 10, 11, 14, 14, 15]
			}]
		},
		options: {
			responsive: true
		}
	});

	//Delete confirm modal
	$("#deleteBtn").on('click', function(e) {
		alert("confirm")
		e.preventDefault();
		$('#deleteModal').modal();
	})


	// Single Bar Chart
	var ctx4 = $("#bar-chart").get(0).getContext("2d");
	var myChart4 = new Chart(ctx4, {
		type: "bar",
		data: {
			labels: ["Italy", "France", "Spain", "USA", "Argentina"],
			datasets: [{
				backgroundColor: [
					"rgba(0, 156, 255, .7)",
					"rgba(0, 156, 255, .6)",
					"rgba(0, 156, 255, .5)",
					"rgba(0, 156, 255, .4)",
					"rgba(0, 156, 255, .3)"
				],
				data: [55, 49, 44, 24, 15]
			}]
		},
		options: {
			responsive: true
		}
	});


	// Pie Chart
	var ctx5 = $("#pie-chart").get(0).getContext("2d");
	var myChart5 = new Chart(ctx5, {
		type: "pie",
		data: {
			labels: ["Italy", "France", "Spain", "USA", "Argentina"],
			datasets: [{
				backgroundColor: [
					"rgba(0, 156, 255, .7)",
					"rgba(0, 156, 255, .6)",
					"rgba(0, 156, 255, .5)",
					"rgba(0, 156, 255, .4)",
					"rgba(0, 156, 255, .3)"
				],
				data: [55, 49, 44, 24, 15]
			}]
		},
		options: {
			responsive: true
		}
	});


	// Doughnut Chart
	var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
	var myChart6 = new Chart(ctx6, {
		type: "doughnut",
		data: {
			labels: ["Italy", "France", "Spain", "USA", "Argentina"],
			datasets: [{
				backgroundColor: [
					"rgba(0, 156, 255, .7)",
					"rgba(0, 156, 255, .6)",
					"rgba(0, 156, 255, .5)",
					"rgba(0, 156, 255, .4)",
					"rgba(0, 156, 255, .3)"
				],
				data: [55, 49, 44, 24, 15]
			}]
		},
		options: {
			responsive: true
		}
	});

	/*******product of js */


})(jQuery);

function editAble(e) {
	var parent = $(e).parent();
	var inputEle = $(parent).find("input");
	if ($(inputEle).attr('id') == undefined) inputEle = $(parent).find("textarea");
	$(inputEle).removeAttr("readonly");
	$(inputEle).removeClass("form-control-plaintext");
	$(inputEle).addClass("form-control");


}
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#imagePreview').css('background-image', 'url(' + e.target.result + ')');
			$('#imagePreview').hide();
			$('#imagePreview').fadeIn(650);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function readURLExtrasImage(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			var imgTag = $(input).parent().find('.extras-img');
			$(imgTag).attr('src',  e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

function saveImage() {
	const PATH = "/shopAdmin"
	var fileInput = $('#imageUpload').prop('files')[0];
	var detailId = $('#productId').val();
	console.log(detailId + " ")
	let URL = "http://localhost:8089/shopAdmin/api/p/img/uploadFile"

	/*公開する時に使うリンク */
	//let path = window.location.protocol + "//" +window.location.hostname;
	//let URL =path+ "/shopAdmin/api/p/img/uploadFile";

	if (fileInput) {
		var fd = new FormData();
		fd.append('file', fileInput);
		fd.append('productId', detailId);
		$.ajax({
			url: URL,
			type: 'post',
			data: fd,
			contentType: false,
			processData: false,
			success: function(response) {
				var imgPath = PATH + response;

				$('#product_image-box').append(
					`
					<div class="px-2 col-3">
										<div class="how-image" onclick="deleteImage(this)">
											<img src="${imgPath}" class="img-thumbnail product-image"
												alt="product-image">
										</div>
										<div class="py-2">
											<input type="radio" name="mainImage" th:value=1
												class="mx-auto d-block">
										</div>
									</div>
					`
				)
				$('#detail_image').append(
					`
					<img src="${imgPath}" class="img-thumbnail product-image "
												alt="product-image">
					`
				)
				$('#add-image').modal('hide');
			},
		});

	}

}
function deleteImage(e) {
	const PATH = "/shopAdmin"
	var imgSrc = $(e).find('img').attr('src');
	if (imgSrc.includes(PATH)) {
		imgSrc = imgSrc.replace(PATH, "");
	}

	let URL = "http://localhost:8089/shopAdmin/api/p/img/delete"

	/*公開する時に使うリンク */
	//let path = window.location.protocol + "//" +window.location.hostname;
	//let URL =path+ "/shopAdmin/api/p/img/delete";
	$.ajax({
		url: URL,
		type: 'GET',
		data: {
			imgName: imgSrc
		},
		success: function(res) {
			if (res) {
				$(e).parent().remove();
			}
		}
	})
}

function delItem(itemId){
	var path = $("#deleteItem").find("a").attr("href");
	var delLink = path + "/del/" + itemId;
	$("#deleteItem").find(".modal-body").html("商品ID<strong>"+itemId+"</strong>を削除します。よろしいでしょうか");
	$("#deleteItem").find("a").attr("href",delLink);
	$("#deleteItem").modal("show")
}
