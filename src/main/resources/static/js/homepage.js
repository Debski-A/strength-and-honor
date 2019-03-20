$(document).ready(function(){
	Tu logika z numerami stron
});

function editPost() {
	console.log("In editPost");
	
}

function deletePost(postId) {
	console.log("deletePost, postId = " + postId + " csrf = " +  $("input[name='_csrf']").val());
	$.ajax({
		url: '/post?postId=' + postId,
		type: 'DELETE',
		headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
		success : function () {
			console.log('success');
			window.location.href = '/';
		},
		error: function (data) {
			console.log(data);
			window.location.href = '/login';
		}
	});
}