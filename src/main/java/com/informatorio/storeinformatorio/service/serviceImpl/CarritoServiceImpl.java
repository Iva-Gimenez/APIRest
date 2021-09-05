package com.informatorio.storeinformatorio.service.serviceImpl;

import com.informatorio.storeinformatorio.entity.*;
import com.informatorio.storeinformatorio.model.CarritoItemModel;
import com.informatorio.storeinformatorio.model.Obervaciones;
import com.informatorio.storeinformatorio.model.ProductModel;
import com.informatorio.storeinformatorio.repository.*;
import com.informatorio.storeinformatorio.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoItemModelRespository carritoItemModelRespository;
    @Autowired
    private ProductModelRepository productModelRepository;
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private CarritoItemsRepository carritoItemsRepository;


    @Override
    public List<Carrito> getAllCarrito() {
        List<Carrito> alls = carritoRepository.findAll();
        for (Carrito i: alls){
            i.getItems().forEach( carritoItem -> {
                i.setTotal(carritoItem.getSubTotal());
            });
        }


        return alls;
    }

    public Carrito createCarrito(User user, String platform){

        Carrito carritoActive = new Carrito();
        carritoActive.setStatus(true);
        carritoActive.setUserId(user);
        carritoActive.setCreatedAt(LocalDate.now());
        carritoActive.setClientPlatform(platform);
        Carrito carritoActiveDB = carritoRepository.save(carritoActive);

        return carritoActiveDB;
    }

    public CarritoItem createCarritoItem(Product productDB, Carrito carritoActiveDB){
        CarritoItem itemActive = carritoItemsRepository.findByCarritoAndProduct(carritoActiveDB, productDB);
        if (itemActive != null){
            itemActive.setQuantity(1.);
            carritoItemsRepository.save(itemActive);
            return itemActive;
        }
        CarritoItem newCarritoItems = new CarritoItem();
        newCarritoItems.setProduct(productDB);
        newCarritoItems.setPrice(productDB.getPrice());
        newCarritoItems.setCarrito(carritoActiveDB);
        newCarritoItems.setQuantity(1.0);
        CarritoItem carritoItemDB = carritoItemsRepository.save(newCarritoItems);

        return carritoItemDB;
    }

    public Carrito totalPrice(Carrito carrito){
        List<CarritoItem> carritoItems = carrito.getItems();
        for (CarritoItem i: carritoItems){
            carrito.setTotal(i.getSubTotal());
        }
        return carrito;
    }

    @Override
    public Carrito addCarritoItem(Long idUser, Long idProduct) {
        User userDB = userRepository.findById(idUser).orElse(null);
        if (userDB == null){
            return null;
        }

        Carrito carritoActive = carritoRepository.findByUserIdAndStatusTrue(userDB);
        Product productDB = productRepository.findById(idProduct).orElse(null);

        if (productDB == null){
            return null;
        }
        if (carritoActive == null){

            Carrito carritoActiveDB = createCarrito(userDB, "Mobile");

            CarritoItem carritoItemDB = createCarritoItem(productDB, carritoActiveDB);

            carritoActiveDB.setItems(carritoItemDB);
            totalPrice(carritoActiveDB);
            carritoRepository.save(carritoActiveDB);

            return carritoActiveDB;
        }
        CarritoItem carritoItemDB = createCarritoItem(productDB, carritoActive);

        if (carritoItemDB.getQuantity() < 2){
            carritoActive.setItems(carritoItemDB);
        }
        totalPrice(carritoActive);
        carritoRepository.save(carritoActive);

        return carritoActive;
    }

    @Override
    public Carrito deleteCarrito(Long idCarrito) {
        Carrito carritoDB = carritoRepository.findById(idCarrito).get();
        carritoDB.setStatus(false);
        carritoRepository.save(carritoDB);
        return carritoDB;
    }

    public ProductModel createProductModel(Product product, Long referenceItemModel){
        ProductModel productModel = ProductModel.builder()
                .name(product.getName())
                .referenceItemModel(referenceItemModel)
                .referenceProductId(product.getId())
                .content(product.getContent())
                .price(product.getPrice())
                .build();
        return productModelRepository.save(productModel);
    }


    public CarritoItemModel createCarritoItemModel(CarritoItem carritoItem, Long idCarrito){
        ProductModel productModel = productModelRepository.findByReferenceItemModel(carritoItem.getId());
        CarritoItemModel carritoItemModel = CarritoItemModel.builder()
                .idCarrito(idCarrito)
                .productModel(productModel)
                .quantity(carritoItem.getQuantity())
                .price(carritoItem.getPrice())
                .subTotal(productModel.getPrice())
                .build();
        return carritoItemModelRespository.save(carritoItemModel);
    }

    @Override
    public Orden checkOut(Long idCarrito, Obervaciones observaciones) {
        Orden ordenDB = ordenRepository.findByIdCarrito(idCarrito);
        if (ordenDB != null){
            return ordenDB;
        }
        Carrito carritoDB = carritoRepository.findByIdAndStatusTrue(idCarrito);
        if (carritoDB == null){
            return null;
        }
        totalPrice(carritoDB);

        for (CarritoItem i: carritoDB.getItems()){
            createProductModel(i.getProduct(), i.getId());
            createCarritoItemModel(i, carritoDB.getId());
        }

       List<CarritoItemModel> list =  carritoItemModelRespository.findByIdCarrito(carritoDB.getId());
        Orden orden = Orden.builder()
                .idCarrito(carritoDB.getId())
                .total(carritoDB.getTotal())
                .createdAt(LocalDate.now())
                .carritoItemModel(list)
                .user(carritoDB.getUserId())
                .status("Cerrado")
                .observacion(observaciones.getObservation())
                .build();
        ordenRepository.save(orden);
        return orden;
    }


}
