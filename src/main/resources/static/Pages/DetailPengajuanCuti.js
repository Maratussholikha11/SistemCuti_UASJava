import {
    Stack,
    styled,
    Table, TableBody,
    TableCell,
    tableCellClasses,
    TableContainer, TableHead,
    TableRow,
    Typography
} from "../js/material-ui.js";

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

function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
}

const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];
function DetailPengajuanCuti(){
    return html`
      <${Stack} padding=${2} spacing=${2}>
            <${Typography} variant="h4" sx=${{color:"#00c853"}}>
                Info Cuti Karyawan
            <//>
            <${Stack} padding=${2} spacing=${2} alignItems="flex-start">
            <${TableContainer} component=${Paper}>
                <${Table} sx=${{ minWidth: 700 }} aria-label="customized table">
                    <${TableHead}>
                        <${TableRow}>
                            <${StyledTableCell}>ID Pengajuan<//>
                            <${StyledTableCell}>ID Employee<//>
                            <${StyledTableCell} align="right">ID HRD<//>
                            <${StyledTableCell} align="right">Nama<//>
                            <${StyledTableCell} align="right">Tanggal Cuti<//>
                            <${StyledTableCell} align="right">Sisa Cuti Tahunan<//>
                            <${StyledTableCell} align="right">ID Karyawan PEngganti<//>
                            <${StyledTableCell} align="right">Lama Cuti<//>
                            <${StyledTableCell} align="right">Alamat<//>
                            <${StyledTableCell} align="right">No telephone<//>
                            <${StyledTableCell} align="right">Keterangan<//>
                            <${StyledTableCell} align="right">Status<//>
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
                            <${StyledTableCell} align="right">${row.calories}<//>
                            <${StyledTableCell} align="right">${row.fat}<//>
                            <${StyledTableCell} align="right">${row.carbs}<//>
                            <${StyledTableCell} align="right">${row.protein}<//>
                            <${StyledTableCell} align="right">${row.calories}<//>
                            <${StyledTableCell} align="right">${row.fat}<//>
                        <//>
                        `)}
                    <//>
                <//>
            <//>
            <//>
    <//>
    `;

}
export default DetailPengajuanCuti;