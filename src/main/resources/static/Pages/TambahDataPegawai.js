import html from "../js/htm.js";
import {
    Button, CircularProgress, IconButton,
    Paper,
    Stack, styled,
    Table,
    TableBody, TableCell, tableCellClasses,
    TableContainer,
    TableHead,
    TableRow,
    Typography
} from "../js/material-ui.js";
import {useEffect, useState} from "../js/react.js";
import MaterialIcon from "../components/MaterialIcon.js";
import InsertEmployee from "../components/InsertEmployee.js";
import api from "../utils/api.js";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: theme.palette.common.black,
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

function TambahDataPegawai(props){

    const [employees, setEmployees] = useState();
    const [deleteOpen, setDeleteOpen] = useState();
    const [deleted, setDeleted] = useState();
    const [deletedIndex, setDeletedIndex] = useState();

    useEffect(() => {
        api('/employees')
            .then(response => response.json())
            .then(employees => {
                setEmployees(employees);
            });
    }, []);


    const [openDialog, setOpenDialog] = useState(false);

    const handleCloseDialog = () => {
        setOpenDialog(false);
    }
    return html`
     <${Stack} padding=${2} spacing=${2} alignItems="flex-start" >
            <${Typography} variant="h4" sx=${{color:"#00c853"}}>
                Tambah Data Pegawai
            <//>
         <${Stack} alignItem="flex-start" direction="row" spacing=${2}>
             <${Button} type="submit" variant="outlined" onClick=${() => setOpenDialog(true)} startIcon=${html`<${MaterialIcon}>add_circle<//>`}>
                 Tambah
             <//>
             <${Button} type="submit" variant="outlined" onClick=${() => setOpenDialog(true)} startIcon=${html`<${MaterialIcon}>add_circle<//>`}>
                 Tambah dari Excel
             <//>
         <//>
         <${InsertEmployee} open=${openDialog} onClose=${handleCloseDialog}/>
        <${TableContainer} component=${Paper}>
                <${Table} sx=${{minWidth: 700}} aria-label="customized table">
                    <${TableHead}>
                        <${TableRow}>
                            <${StyledTableCell}>ID Eployee<//>
                            <${StyledTableCell} align="center">Nama<//>
                            <${StyledTableCell} align="center">NIP<//>
                            <${StyledTableCell} align="center">Divisi<//>
                            <${StyledTableCell} align="center">Email<//>
                            <${StyledTableCell} align="center">Tindakan<//>
                        <//>
                    <//>
                    <${TableBody}>
                        ${employees ? employees.map(employee => html`
                        <${StyledTableRow} key=${employee.id}>
                            <${StyledTableCell} align="center">${employee.id}<//>
                            <${StyledTableCell} align="center">${employee.namaLengkap}<//>
                            <${StyledTableCell} align="center">${employee.nip}<//>
                           
                            <${StyledTableCell} align="center">${employee.divisi}<//>
                            <${StyledTableCell} align="center">${employee.email}<//>
                            <${StyledTableCell} align="center">
                                <${IconButton}>
                                    <${MaterialIcon}>edit_note<//>
                                <//>
                                <${IconButton}>
                                    <${MaterialIcon}>delete<//>
                                <//>
                            <//>
                        <//>
                        `) : html`<${CircularProgress} />`}
                    <//>
                <//>
        <//>
     <//>
    `;
}
export default TambahDataPegawai;