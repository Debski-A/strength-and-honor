var globalNumberOfPosts;

$(document).ready(function(){
	globalNumberOfPosts = $('#numberOfPosts').val();
	generatePageNavigationButtons();
});

function generatePageNavigationButtons() {
	if (globalNumberOfPosts > 1) {
		var numberOfPages = parseInt(globalNumberOfPosts / 5);
		var numberOfPostsOnLastPage = globalNumberOfPosts % 5;

		if (numberOfPostsOnLastPage !== 0) {
		    numberOfPages++;
		}

		var firstPage = 1;
		var lastPage = numberOfPages;
		
		var firstPageButton =  $('<button/>', {
	        text: firstPage, 
	        id: 'firstPageButton',
	        class: 'pageNavigationButton btn btn-sm',
	        click: function () { 
	        	alert('firstPageButton clicked'); 
	        }
	    });
		
		var inputInMiddle = $('<input/>', {
			type: 'text',
	        text: '...',
	        placeholder: '...',
	        id: 'inputInMiddle',
	        class: 'pageNavigationInput'
	    });
		
		var lastPageButton =  $('<button/>', {
	        text: lastPage, 
	        id: 'lastPageButton',
	        class: 'pageNavigationButton btn btn-sm',
	        click: function () { 
	        	alert('lastPageButton clicked'); 
	        }
	    });
		
		$("#selectPageButtons").append(firstPageButton);
		$("#selectPageButtons").append(inputInMiddle);
		$("#selectPageButtons").append(lastPageButton);
	}
}

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
			window.location.href = '/';
		},
		error: function (data) {
			console.log(data);
			window.location.href = '/login';
		}
	});
}