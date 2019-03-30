datacenter = "dc1"
client_addr = "0.0.0.0"
ports {
  grpc = 8502
}
connect {
  enabled = true
}
services {
  name = "demo"
  port = 8080
  connect {
    sidecar_service {
      proxy {
        config {
          envoy_public_listener_json = <<EOL
          {
            "@type": "type.googleapis.com/envoy.api.v2.Listener",
            "name": "public_listener:0.0.0.0:21000",
            "address": {
              "socketAddress": {
                "address": "0.0.0.0",
                "portValue": 21000
              }
            },
            "filterChains": [
              {
                "filters": [
                  {
                    "name": "envoy.http_connection_manager",
                    "config": {
                      "stat_prefix": "public_listener",
                      "route_config": {
                        "name": "local_route",
                        "virtual_hosts": [
                          {
                            "name": "backend",
                            "domains": ["*"],
                            "routes": [
                              {
                                "match": {
                                  "prefix": "/"
                                },
                                "route": {
                                  "cluster": "local_app"
                                }
                              }
                            ]
                          }
                        ]
                      },
                      "http_filters": [
                        {
                          "name": "envoy.router",
                          "config": {}
                        }
                      ]
                    }
                  }
                ]
              }
            ]
          }
          EOL
        }
        upstreams = [
          {
            destination_name = "demo"
            local_bind_port = 10000
            config {
              envoy_listener_json = <<EOL
              {
                "@type": "type.googleapis.com/envoy.api.v2.Listener",
                "name": "dynamic_listener:127.0.0.1:10000",
                "address": {
                  "socketAddress": {
                    "address": "127.0.0.1",
                    "portValue": 10000
                  }
                },
                "filterChains": [
                  {
                    "filters": [
                      {
                        "name": "envoy.http_connection_manager",
                        "config": {
                          "stat_prefix": "dynamic_listener",
                          "route_config": {
                            "name": "dynamic_route",
                            "virtual_hosts": [
                              {
                                "name": "backend",
                                "domains": ["*"],
                                "routes": [
                                  {
                                    "match": {
                                      "prefix": "/"
                                    },
                                    "route": {
                                      "cluster_header": "SERVICE"
                                    }
                                  }
                                ]
                              }
                            ]
                          },
                          "http_filters": [
                            {
                              "name": "envoy.router",
                              "config": {}
                            }
                          ]
                        }
                      }
                    ]
                  }
                ]
              }
              EOL

            }
          },
          {
            destination_name = "user"
            local_bind_port = 10001
          }
        ]
      }
    }
  }
}