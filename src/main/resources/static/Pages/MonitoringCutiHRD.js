import html from "../js/htm.js";
import {
    Button, IconButton,
    Paper, Stack,
    styled,
    Table,
    TableBody,
    TableCell,
    tableCellClasses,
    TableContainer,
    TableHead,
    TableRow, Typography
} from "../js/material-ui.js";
import MaterialIcon from "../components/MaterialIcon.js";
import {Link} from "../js/react-router-dom.js";
import InsertCuti from "../components/InsertCuti.js";
import InsertEmployee from "../components/InsertEmployee.js";
import {useState} from "../js/react.js";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor:"#512da8",
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    '&:nth-of-type(odd)': {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    '&:last-child td, &:last-child th': {
        border: 0,
    },
}));



function MonitoringCutiHRD() {

    return html`
         <${Stack} padding=${2} spacing=${2}>
            <${Typography} variant="h4" sx=${{color:"#00c853"}}>
                Monitoring
            <//>
            <${Stack} padding=${2} spacing=${2} alignItems="flex-start">
            <${TableContainer} component=${Paper} >
                <${Table} sx=${{ minWidth: 700}} aria-label="customized table">
                    <${TableHead} >
                        <${TableRow}>
                            <${StyledTableCell}>ID Pengajuan<//>
                            <${StyledTableCell} align="right">Nama<//>
                            <${StyledTableCell} align="right">Tanggal Cuti<//>
                            <${StyledTableCell} align="right">Lama Cuti<//>
                            <${StyledTableCell} align="right">Status<//>
                            <${StyledTableCell} align="right">Tindakan<//>
                        <//>
                    <//>
                    <${TableBody}>
                        ${rows.map((row) => html`
                        <${StyledTableRow} key=${row.name}>
                            <${StyledTableCell} component="th" scope="row">
                                ${row.name}
                            <//>
                            <${StyledTableCell} align="right">${row.calories}<//>
                            <${StyledTableCell} align="right">${row.fat}<//>
                            <${StyledTableCell} align="right">${row.carbs}<//>
                            <${StyledTableCell} align="right">${row.protein}<//>
                            <${StyledTableCell} align="right">
                                <${IconButton}>
                                    <${MaterialIcon}>check_circle_outline<//>
                                <//>
                                <${IconButton}>
                                    <${MaterialIcon}>highlight_off<//>
                                <//>
                            <//>
                        <//>
                        `)}
                    <//>
                <//>
            <//>
            <//>
    <//>
    `;
}

export default MonitoringCutiHRD;