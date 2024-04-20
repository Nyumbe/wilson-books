import React from "react";
import ReactDOM from "react-dom/client";
import { App } from "./App";
import "./index.css";
import { Providers } from "./providers/Providers";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <Providers>
      <App />
    </Providers>
  </React.StrictMode>
);

// git filter-branch --env-filter '
// OLD_EMAILS="moga.m.abdulwahab@gmail.com"
// CORRECT_NAME="Nyumbe"
// CORRECT_EMAIL="wilsonnyumbe99@gmail.com"

// if echo "$OLD_EMAILS" | grep -q "$GIT_COMMITTER_EMAIL"; then
// export GIT_COMMITTER_NAME="$CORRECT_NAME"
// export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
// fi
// if echo "$OLD_EMAILS" | grep -q "$GIT_AUTHOR_EMAIL"; then
// export GIT_AUTHOR_NAME="$CORRECT_NAME"
// export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
// fi
// ' --tag-name-filter cat -- --branches --tags
