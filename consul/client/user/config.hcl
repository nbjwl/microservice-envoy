datacenter = "dc1"
client_addr = "0.0.0.0"
ports {
  grpc = 8502
}
connect {
  enabled = true
}
services {
  name = "user"
  port = 8080
  connect {
    sidecar_service {
    }
  }
}
