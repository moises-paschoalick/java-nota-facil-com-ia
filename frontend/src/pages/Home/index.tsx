import Footer from "components/Footer";
import NavBar from "components/NavBar";
import { useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();

    return (
        <>
            <NavBar />
            <div className="container py-5">
                <div className="jumbotron">
                    <h1 className="display-4">Gastos Financeiros</h1>
                    <p className="lead">Analise seus gastos financeiros e transações por diferentes perspectivas</p>
                    <hr />
                    <p>Esta aplicação consiste em exibir um dashboard a partir de dados fornecidos por um back end construído com Spring Boot.</p>
                    <button 
                        onClick={() => navigate('/dashboard')} 
                        className="btn btn-primary btn-lg"
                    >
                        Acessar dashboard
                    </button>
                </div>
            </div>
            <Footer/>
       </> 
    );
}

export default Home;

