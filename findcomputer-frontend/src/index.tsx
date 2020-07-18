import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter,Route, Switch } from "react-router-dom";
import { Home } from './pages/Home';
import { ItemPage } from './pages/ItemPage';
import { TransactionPage } from './pages/TransactionPage';
import { LoginPage } from './pages/LoginPage';
import { MuiThemeProvider, ThemeProvider, createMuiTheme } from '@material-ui/core';
import createTypography from '@material-ui/core/styles/createTypography';
import createPalette from '@material-ui/core/styles/createPalette';

export default function App(): JSX.Element {
    const THEME = (() => {
        const palette = createPalette({
          type: 'light',
        });
      
        const typography = createTypography(palette, {
          fontFamily: '"Quicksand"',
        });
      
        return createMuiTheme({
          palette: palette,
          typography: typography,
        });
      })();
    
    return (
        <MuiThemeProvider theme={THEME}>
        <BrowserRouter>
            <Switch>
                <Route
                    path = "/" exact component={Home}
                />
                <Route
                    path = "/login" exact component={LoginPage}
                />
                <Route
                    path = "/items" exact component={ItemPage}
                />
                <Route
                    path="/transactions" exact component={TransactionPage}
                />
                <Route
                    path = "/" component={() => <div>404 - page not found</div>}
                />
            </Switch>

        </BrowserRouter>
        </MuiThemeProvider>
                
    )
}

const root = document.getElementById('app-root')

ReactDOM.render(<App/>, root)