import DataTable from "components/DataTable";
import Footer from "components/Footer";
import NavBar from "components/NavBar";
import TotalCard from "components/TotalCard";
import "./styles.css";

const Dashboard = () => {
    return (
        <>
            <NavBar />
            <div className="container">
                <h1 className="text-primary py-3">Dashboard de Gastos Financeiros</h1>

                <div className="dashboard-cards">
                    <TotalCard />
                </div>

                <div className="py-3">
                    <h2 className="text-primary">Todas as Transações</h2>
                </div>
                <DataTable />
            </div>
            <Footer />
        </>
    );
}

export default Dashboard;

