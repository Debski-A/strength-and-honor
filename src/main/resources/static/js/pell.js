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

