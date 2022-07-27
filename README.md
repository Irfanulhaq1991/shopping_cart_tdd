## SadaParcel
### Details
 - Architecture : MVVM
 - publish subscribe: Live Data
 - UI state management: Kotlin Seal Classes
 - Sychroniztion: Kotlin Coroutine
 - Development Strategy: Outside-in TDD
 - Domain Mapping: To avoid more complexity by adding separate use cases, the serrvice manager at the boundery behind the repositoyr is reponsible for mapping Madata to and from domain.
      also, the reponsitory is getting prepared data which further mapped in to states. 
 - Exception Handling: One Generic App Exception created to decouple the undelying network, database, or any other error or exception. This served my purpose 
    for now but separete exceptions for each feature could be added as well.
 -  Ui Tests: Skipped due to time constraints. However, the acceptance test lead the happ path.
 - Dependecy Injection: The dependencies are provided manually at the moment to avoid complexities for now. However, hilt or koin for dependency Injection could be added later.
 - Response handling: to gain more controll over the response, it is handled in remote service.
 - FakeDbAPI: The shopping cart item could be added to datbase. A fake in memrory database which is actually a mutable list is serving the purpose.
 - FakeRemoteApi: Local dummy data is working as mocked response. Another mocked remote version is created at "https://mocki.io". 
 
#### Note
    This is a MVP(minimum vioble product). Therefore, few test cases and functionalities like incrementing & decrementing is left along with 
    Ui like confirm and total ammount.

    

