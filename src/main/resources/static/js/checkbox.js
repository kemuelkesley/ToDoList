document.addEventListener('DOMContentLoaded', function() {
  var checkboxes = document.querySelectorAll('input[type="checkbox"]');

  checkboxes.forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
      var descricao = this.parentNode.previousElementSibling;
      if (this.checked) {
        descricao.style.color = '#7FB77E';
      } else {
        descricao.style.color = '';
      }
    });
  });
});
