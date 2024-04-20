import React from "react";
import { ReduxProvider } from "./ReduxProvider";
import { AppThemeProvider } from "./AppThemeProvider";
import { ReactQueryProvider } from "./ReactQueryProvider";

export const Providers = (props) => {
  return (
    <ReduxProvider>
      <AppThemeProvider>
        <ReactQueryProvider>{props.children}</ReactQueryProvider>
      </AppThemeProvider>
    </ReduxProvider>
  );
};
