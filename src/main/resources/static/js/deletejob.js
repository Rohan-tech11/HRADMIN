

 $('document').ready(function(){
	
	
	
	$('table #deleteButton').on('click', function(event){
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$('#confirmDeleteButton').attr('href', href);
		
		$('#deleteModal').modal();
	});
		
});

 $('document').ready(function(){
	
	
	
	$('table #applyButton').on('click', function(event){
		event.preventDefault();
		var href= $(this).attr('href');
				$('#confirmApply').attr('href', href);

		
		
		
		$('#applyModal').modal();
	});
		
});


$('document').ready(function(){
	
	
	
	$('table #WithDrawButton').on('click', function(event){
		event.preventDefault();
		var href= $(this).attr('href');
				$('#confirmWithdraw').attr('href', href);

		
		
		
		$('#deleteModal').modal();
	});
		
});