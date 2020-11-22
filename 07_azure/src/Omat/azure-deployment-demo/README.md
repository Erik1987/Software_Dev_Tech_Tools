# Azure deployment demo project

This project is built to demonstrate different approaches for deploying a Node.js microservice in Azure.

Demonstrations include:

* Virtual machine deployment on Ubuntu 18.04
* App service deployment
* Azure Functions deployment
* Container deployment with Docker and Azure Container Registry

## Application features

The demo application contains two HTTP endpoints and runs on top of [Express](https://expressjs.com/). You can check the contents of index.js [here](https://github.com/haagahelia/app-deployment-demo/blob/master/index.js).

In short, the server root `/` will return a list of events from [MyHelsinki Open API](http://open-api.myhelsinki.fi/) and the `/health` path will return a JSON object describing the status of the application:

```json
{
  "status": "ok",
  "eventCount": 4667,
  "duration": 1.4089999198913574,
  "port": "80"
}
```

`eventCount` shows how many events from [MyHelsinki Open API](http://open-api.myhelsinki.fi/) were received and `duration` shows the duration of the request to the API. `port` displays the port that the application is listening to internally.

## License

The data from [MyHelsinki Open API](http://open-api.myhelsinki.fi/) is licensed under [Creative Commons By 4.0 license](http://open-api.myhelsinki.fi/terms).
