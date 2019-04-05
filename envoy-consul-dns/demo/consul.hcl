datacenter = "dc1"
client_addr = "0.0.0.0"
service {
  name = "demo"
  port = 21000
  check {
    http = "http://localhost:21000/actuator/health"
    interval = "10s"
    timeout = "1s"
  }
}
