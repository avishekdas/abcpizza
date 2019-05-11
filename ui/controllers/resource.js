var url = require('url');
var fs = require('fs');
var path = require('path');
const Client = require('node-rest-client').Client;
var client = new Client();

exports.search = function(req, res) {
    var string = JSON.stringify(req.body);
    var doc = JSON.parse(string);
    
	//Call api
	var docurl = "http://localhost:9292/api/orderstatus/find";
    
    var args = {
		data: doc,
		headers: { "Content-Type": "application/json" }
	};
	 
	client.post(docurl, args, function (data, response) {
		var obj = JSON.parse(data.status);
		res.send(JSON.stringify(obj));
	});
	
};

