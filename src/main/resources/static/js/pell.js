const editor = pell.init({
  element: document.getElementById('editor'),
  onChange: html => {
    document.getElementById('html-output').innerHTML = html
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
		name: 'publishPL',
		icon: 'PublishPL',
		title: 'PublishPL',
		result: () => {
			var content = {};
			content['content'] =  document.getElementById('html-output').innerHTML;
			//var encodedContent = btoa(content);
			$.ajax({
				url: 'post',
				type: 'POST',
				dataType : 'json',
				'contentType': 'application/json',
				headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
				data: content,
				success : function (data) {
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

// editor.content<HTMLElement>
// To change the editor's content:
editor.content.innerHTML = '<b><u><i>Initial content!</i></u></b>'