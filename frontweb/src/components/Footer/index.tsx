import "./styles.css";

const Footer = () => {
    return (
        <footer className="footer mt-auto py-3 bg-dark">
            <div className="container">
                <p className="text-light">© 2023 Onnipro. Todos os direitos reservados. São Paulo | Fortaleza | Lisboa</p>
                <p className="text-light">
                    <small>
                        E-mail: <a href="mailto:contato@onnipro.com.br" className="text-light">contato@onnipro.com.br</a> | 
                        Telefone: <a href="tel:+551135040390" className="text-light">+55 (11) 3504-0390</a>
                    </small>
                </p>
            </div>
        </footer>
    );
}

export default Footer;