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
			var csrfParameter = '${_csrf.parameterName}';
		    var csrfToken = '${_csrf.token}';
		    alert(csrfParameter);
		    alert(csrfToken);
			var content = {};
			content['content'] =  document.getElementById('html-output').innerHTML;
			content[csrfParameter] = csrfToken;
			alert(content);
			//var encodedContent = btoa(content);
			$.ajax({
				url: '/saveDivContentToDatabase',
				type: 'POST',
				contentType: 'application/json; charset=utf-8',
				dataType: 'json',
				data: content,
				success : function (data) {
					alert("Poszło");
				}
			});
		}
    }
  ],
  classes: {
    actionbar: 'pell-actionbar-custom-name',
    button: 'pell-button-custom-name',
    content: 'pell-content-custom-name',
    selected: 'pell-button-selected-custom-name'
  }
})

// editor.content<HTMLElement>
// To change the editor's content:
editor.content.innerHTML = '<b><u><i>Initial content!</i></u></b>'