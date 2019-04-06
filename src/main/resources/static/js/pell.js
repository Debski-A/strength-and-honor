const editor = pell.init({
  element: document.getElementById('editor'),
  onChange: html => {
    // do nothing onChange
  },
  defaultParagraphSeparator: 'p',
  styleWithCSS: false,
  actions: [
	'heading1',
	'heading2',
	'paragraph',
    'bold',
    'underline',
    'italic',
    'olist',
    'ulist',
    'image',
    'link',
    {
		name: 'justifyLeft',
		icon: 'JL',
		title: 'Justify Left',
		result: () => pell.exec('justifyLeft')
    },
    {
		name: 'justifyCenter',
		icon: 'JC',
		title: 'Justify Center',
		result: () => pell.exec('justifyCenter')
    },
    {
		name: 'justifyRight',
		icon: 'JR',
		title: 'Justify Right',
		result: () => pell.exec('justifyRight')
    },
    {
		name: 'publish',
		icon: 'Publish',
		title: 'Publish',
		result: () => {
			var content = {};
			content['content'] =  editor.content.innerHTML;
			content['latestUpdate'] = currentDate();
			//content['postId'] = jesli bedzie podane id to update zamiast save
			$.ajax({
				url: 'post',
				type: 'POST',
				async: true,
				contentType: "application/json; charset=utf-8",
				headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
				data: JSON.stringify(content),
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
    }
  ],
  classes: {
    actionbar: 'pell-actionbar-custom-name',
    button: 'pell-button-custom-name',
    content: 'pell-content-gray',
    selected: 'pell-button-selected-custom-name'
  }
})

function currentDate() {
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();


	var output = d.getFullYear() + '-' +
	    ((''+month).length<2 ? '0' : '') + month + '-' +
	    ((''+day).length<2 ? '0' : '') + day;
	
	return output;
}