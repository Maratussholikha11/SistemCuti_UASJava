import html from "./js/htm.js";
import {Button, CircularProgress, Container} from "./js/material-ui.js";
import {BrowserRouter, Link, Route} from "./js/react-router-dom.js";
import {createContext, lazy, Suspense, useEffect, useState} from "./js/react.js";
import Header from "./components/Header.js";
import NavDrawer from "./components/NavDrawer.js";
import MonitoringCutiHRD from "./Pages/MonitoringCutiHRD.js";
import InfoCutiKaryawan from "./Pages/InfoCutiKaryawan.js";
import LaporanCuti from "./Pages/LaporanCuti.js";
import TambahDataPegawai from "./Pages/TambahDataPegawai.js";
import Logout from "./components/Logout.js";
import GantiPassword from "./components/GantiPassword.js";
import DashboardKaryawan from "./Pages/DashboardKaryawan.js";
import MonitoringKaryawan from "./Pages/MonitoringKaryawan.js";
import Login from "./Pages/Login.js";

const DashboardHRD = lazy(() => import("./Pages/DashboardHRD.js"));
const InsertCuti = lazy(() => import("./components/InsertCuti.js"));
const InsertEmployee = lazy(() => import("./components/InsertEmployee.js"));

export const AuthContext = createContext();

export async function getUser() {
    const response = await fetch('/api/auth/me', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token'),
            'Content-Type': 'application/json'
        },
    })
    return response.json();
}

function App() {

    const [drawerOpen, setDrawerOpen] = useState(false);
    const [user, setUser] = useState(null);

    useEffect(async () => {
        const token = localStorage.getItem('token');
        if (token) {
            const user = await getUser();
            setUser(user);
        }
    }, []);

    return html`
        <${AuthContext.Provider} value=${{user, setUser}}>
            <${BrowserRouter}>
                <${Header} toggleDrawer=${() => setDrawerOpen(true)}/>
                <${NavDrawer} open=${drawerOpen} onClose=${() => setDrawerOpen(false)}/>
                <${Container} maxWidth="xl">
                    <${Suspense} fallback=${html`<${CircularProgress}/>`}>
                        <${Route} exact path="/">
                            <${DashboardHRD}/>
                        <//>
                        <${Route} exact path="/monitoring">
                            <${MonitoringCutiHRD}/>
                        <//>
                        <${Route} exact path="/insertcuti">
                            <${InsertCuti}/>
                        <//>
                        <${Route} exact path="/infocutikaryawan">
                            <${InfoCutiKaryawan}/>
                        <//>
                        <${Route} exact path="/laporancuti">
                            <${LaporanCuti}/>
                        <//>
                        <${Route} exact path="/tambahdatakaryawan">
                            <${TambahDataPegawai}/>
                        <//>
                        <${Route} exact path="/insertemployee">
                            <${InsertEmployee}/>
                        <//>
                        <${Route} exact path="/logout">
                            <${Logout}/>
                        <//>
                        <${Route} exact path="/gantipassword">
                            <${GantiPassword}/>
                        <//>
        
                        <${Route} exact path="/dashboardkaryawan">
                            <${DashboardKaryawan}/>
                        <//>
                        <${Route} exact path="/monitoringkaryawan">
                            <${MonitoringKaryawan}/>
                        <//>
        
                        <${Route} exact path="/login">
                            <${Login}/>
                        <//>
                       
                    <//>
                <//>
            <//>
        <//>
    `;
}

export default App;