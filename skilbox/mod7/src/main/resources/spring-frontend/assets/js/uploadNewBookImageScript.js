$('#uploadButton').on('click', function () {
alert(123)
 $('#dialog').trigger('click');
});

$('#dialog').on('change',function (){
alert(333123)

 $('#imgForm').submit();
});