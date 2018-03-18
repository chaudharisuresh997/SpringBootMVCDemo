<script>
$(document).ready(function() {
	$("#navbarDropdown").click(function(){
		
		 $.ajax({
		url:"http://localhost:8080/getPlatFormList/",
		type:"GET",
		success:function(responceData){
			var list=responceData;
			$("#platForms").empty();
			for(i=0;i<list.length;i++)
				{
				var item="/getGameByPlatForm/"+list[i];
				
				$("#platForms").append('<a class="dropdown-item" href="'+item+'">'+list[i]+'</a>');
				}
		},
		dataType: 'json'		
	});
	});
});
function sort(sort)
{
	 $.ajax({
			url:"http://localhost:8080/getGameByScore/",
			type:"GET",
			data:"score="+JSON.stringify(sort),
			success:function(responceData){
				$(".list-group").empty();
				var list=responceData;
				
				for(i=0;i<list.length;i++)
				{
				$(".list-group").append('<a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">');
				$(".list-group").append('<div class="d-flex w-100 justify-content-between">');    
				$(".list-group").append('<h5 class="mb-1">'+list[i].title+'</h5>');
				$(".list-group").append('<small>'+list[i].score+'</small>');
				$(".list-group").append('</div>');
				$(".list-group").append('<p class="mb-1">'+list[i].platform+'</p>');
				$(".list-group").append('<small>'+list[i].genre+'</small>');
				$(".list-group").append('</a>');		
				}
			},
			dataType: 'json'		
		});
		
	}
function searchByName()
{
	var name1=$("#search").val();
	 $.ajax({
			url:"http://localhost:8080/getGameByName/",
			type:"GET",
			data:"name="+name1,
			success:function(responceData){
				$(".list-group").empty();
				var list=responceData;
				
				for(i=0;i<list.length;i++)
				{
				$(".list-group").append('<a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">');
				$(".list-group").append('<div class="d-flex w-100 justify-content-between">');    
				$(".list-group").append('<h5 class="mb-1">'+list[i].title+'</h5>');
				$(".list-group").append('<small>'+list[i].score+'</small>');
				$(".list-group").append('</div>');
				$(".list-group").append('<p class="mb-1">'+list[i].platform+'</p>');
				$(".list-group").append('<small>'+list[i].genre+'</small>');
				$(".list-group").append('</a>');		
				}
			},
			dataType: 'json'		
		});
		
	}
</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Game Zone</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          PlatForm
        </a>
        <div class="dropdown-menu" id="platForms" aria-labelledby="navbarDropdown">
          
         
          <div class="dropdown-divider"></div>
         
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownScore" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          SortByScore
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" onClick="sort('ASC')">Asc</a>
          <a class="dropdown-item" onClick="sort('DESC')">Desc</a>
         
          <div class="dropdown-divider"></div>
         
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" id="search" type="search" placeholder="Search" aria-label="Search">
      <input type="button" class="btn btn-success" onClick="searchByName()" value="Search"/>
    </form>
  </div>
</nav>