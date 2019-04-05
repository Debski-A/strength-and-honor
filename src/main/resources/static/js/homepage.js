var globalNumberOfPosts;
var currentPage;

$(document).ready(function(){
	globalNumberOfPosts = $('#numberOfPosts').val();
	if (currentPage === null) {
		currentPage = 1;
	}
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
	        class: 'pageNavigation',
	        click: function () { 
	        	window.location.href = '/';
	        }
	    });
		
		var inputInMiddle = $('<input/>', {
			type: 'text',
	        text: '...',
	        placeholder: '...',
	        id: 'inputInMiddle',
	        class: 'pageNavigation'
	    });
		
		var lastPageButton =  $('<button/>', {
	        text: lastPage, 
	        id: 'lastPageButton',
	        class: 'pageNavigation',
	        click: function () { 
	        	var offset = calculateOffsetForLastPage(lastPage);
	        	window.location.href = '/?offset=' + offset; 
	        }
	    });
		
		$("#selectPageButtons").append(firstPageButton);
		$("#selectPageButtons").append(inputInMiddle);
		$("#selectPageButtons").append(lastPageButton);
	}
}

function calculateOffsetForLastPage(lastPage) {
	return lastPage * 5;
}

function editPost() {
	console.log("In editPost");
	
}

function deletePost(postId) {
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