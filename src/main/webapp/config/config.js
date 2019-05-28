const config={
	url:"http://192.168.4.108:9200"
};

function post(url,params,fun)
	{
		 $.post(url, params,fun);
	}