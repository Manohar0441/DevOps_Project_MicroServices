const express = require("express");
const cors = require("cors");

const app = express();

app.use(cors());
app.use(express.json());

// Fake token storage for demo
let authToken = null;

// ✅ Health check
app.get("/health", (req, res) => {
  res.status(200).json({ status: "OK" });
});

// ✅ Root (Protected)
app.get("/", (req, res) => {

  // Check if authenticated
  const token = req.headers.authorization;

  if (!token || token !== authToken) {
    return res.status(401).json({
      message: "Unauthorized - Please Login",
    });
  }

  res.send("Auth Service Running");
});

// ✅ Login Route
app.post("/api/auth/login", (req, res) => {
  const { username, password } = req.body;

  if (username === "admin" && password === "1234") {

    // Generate fake token
    authToken = "abc123";

    return res.json({
      message: "Login Successful",
      token: authToken,
    });
  }

  res.status(401).json({
    message: "Invalid Credentials",
  });
});

// ✅ Verify Auth Every Time Website Loads
app.get("/api/auth/verify", (req, res) => {

  const token = req.headers.authorization;

  if (!token || token !== authToken) {
    return res.status(401).json({
      authenticated: false,
      message: "Unauthorized",
    });
  }

  res.status(200).json({
    authenticated: true,
    message: "User Verified",
  });
});
// ✅ Logout Route
app.post("/api/auth/logout", (req, res) => {

  authToken = null;

  res.json({
    message: "Logged Out Successfully",
  });
});

app.listen(3001, () => {
  console.log("Auth Service running on port 3001");
});