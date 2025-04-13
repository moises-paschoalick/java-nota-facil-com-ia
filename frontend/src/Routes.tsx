import { BrowserRouter, Routes as RouterRoutes, Route } from "react-router-dom";
import Home from "pages/Home";
import Dashboard from "pages/Dashboard";

const Routes = () => {
    return (
      <BrowserRouter>
        <RouterRoutes>
          <Route path="/" element={<Home />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </RouterRoutes>
      </BrowserRouter>        
    );
}

export default Routes;