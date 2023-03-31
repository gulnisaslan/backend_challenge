# BACKEND CHALLENGE 

## Açık Uçlu Soruların Cevapları
[Enoca Soru ve Cevaplar.pdf ](/Enoca_case_sorular_ve_cevaplari.pdf)

Yaptığım projede customer ve order entityleri var. Dtolar ile gerekli olan verileri kullanıcıdan almayı hedefledim.Aşagıda görüldüğü gibi entity,Dto,Service ve controller katmanları olusturuldu.Dökümantasyon için Swaager kullanıldı.Projeyi ayağa kaldırdıktan sonra [http://localhost:8021/swagger-ui/index.html#/](http://localhost:8021/swagger-ui/index.html#/) tıklarsanız swagger dökümantasyonuna gidersiniz ve endpointleri deneyebilirsiniz.


## Kullandığım Teknolojiler
ModelMapper
PostgreSql
Swagger
Lombok
Validation
Spring Web

# Customer
## Entity
        @Entity
        @Table(name = "customers")
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public class Customer {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Long id;
            private String name;
            private Short age;

        

        @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
        @JsonIgnore
            private List<Order> orders;
        }

## DTO

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class CustomerRequestDto {
            private String name;
            private Short age;
        }

        
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class CustomerResponseDto {
            private String name;
            private Short age;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class CustomerUpdateDto {
            private Long id;
            private String name;
            private Short age;
        }

# Service Katmanı
        List<CustomerResponseDto> getAllCustomer();
        CustomerResponseDto getByCustomerId(Long id);
        CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);    
        CustomerResponseDto updateCustomer(CustomerUpdateDto customerUpdateDto);    
        CustomerResponseDto deleteCustomer(Long id);
        List<Customer> findCustomersWithoutOrders();
        List<Customer> findWithOrdersByContainingWord(String word);


# Controller
![Customer](/photos/CustomerController.png)

# Order
## Entity
        @Entity
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        @Table(name="orders")
        public class Order {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name="id")
            private Long id;
            private Double totalPrice;
            private LocalDateTime createDate = LocalDateTime.now();


            @ManyToOne( )
            @JoinColumn(name = "customer_id")
            private Customer customer;

        }

## DTO
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public class OrderRequestDto {
        
            private Double totalPrice;
            private Long customerId;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class OrderResponseDto {
            private Long id;
            private Double totalPrice;
            private LocalDateTime createDate ;
            private Long customerId;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class OrderUpdateDto {
            private Long id;
            private Double totalPrice;
            
        }

## Service Katmanı

        OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
            OrderResponseDto updateOrder(OrderUpdateDto orderRequestDto);
            OrderResponseDto deleteOrder(Long id);
            OrderResponseDto getByOrderId(Long id);
            List<OrderResponseDto> getByOrder();
            List<Order> findByAllOrderCreateDate(LocalDateTime createDate)


## Controller
![](/photos/OrderController.png)
