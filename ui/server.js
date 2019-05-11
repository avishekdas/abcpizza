var express = require("express");
var session = require('express-session');
var log4js = require('log4js');
var bodyParser = require('body-parser');
var querystring = require('querystring');
var http = require('http');
var fs = require('fs');
var url = require('url');

var	app = express();
var logger = log4js.getLogger("abcpizzaui");

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

var port = process.env.PORT || 8080;

app.use(express.static(__dirname + '/public'));

app.get("/getData", function (request, response) {
  getStatus();
  response.end("");
});

var querystring = require('querystring');
var http = require('http');
var host = 'localhost';
var port = '8080';

var resController = require('./controllers/resource.js');

// Create our Express router
var router = express.Router();

// Create endpoint handlers for resources
router.route('/search')
  .post(resController.search);
  
// Register all our routes with /
app.use('/', router);

var port = (process.env.VCAP_APP_PORT || 8080);
var host = (process.env.VCAP_APP_HOST || 'localhost');
app.listen(port);
console.log("Listening on port ", port);

function performRequest(endpoint, method, data, success) {
  var dataString = JSON.stringify(data);
  var headers = {};
  
  if (method == 'GET') {
    endpoint += '?' + querystring.stringify(data);
  }
  else {
    headers = {
      'Content-Type': 'application/json',
      'Content-Length': dataString.length
    };
  }
  var options = {
    host: host,
	port: port,
    path: endpoint,
    method: method,
    headers: headers
  };

  var req = http.request(options, function(res) {
    res.setEncoding('utf-8');

    var responseString = '';

    res.on('data', function(data) {
      responseString += data;
    });

    res.on('end', function() {
      console.log(responseString);
      var responseObject = JSON.parse(responseString);
      success(responseObject);
    });
  });

  req.write(dataString);
  req.end();
}

function getStatus() {
  performRequest('/status.json', 'GET', {
    "_items_per_page": 100
  }, function(data) {
    console.log('Fetched ' + data.result);
  });
}