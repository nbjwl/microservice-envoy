datacenter = "dc1"
client_addr = "0.0.0.0"
connect {
  enabled = true
}
ports {
  grpc = 8502
}
services {
  name = "demo"
  port = 8080
  connect {
    sidecar_service {}
  }
}
