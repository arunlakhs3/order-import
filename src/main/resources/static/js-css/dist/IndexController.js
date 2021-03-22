app.controller('IndexController', function ($scope, $http, $window, MyService, Upload, uiGridConstants)
{
   vm = $scope;

   vm.isLoading = false;

   vm.isDataPopulated = false;

   $window.scrollTo(0, 0);
   MyService.setLoadingPage('none', '');

   vm.gridOptions = {
      enablePagination: true,
      enablePaginationControls: true,
      paginationPageSizes: [200],
      useExternalPagination: true,
      selectionRowHeaderWidth: 45,
      selectionRowHeaderHeight: 35,
      showGridFooter: true,
      treeRowHeaderAlwaysVisible: false,
      data: [],
      totalItems: 0,
      columnDefs: [{
            field: "region",
            displayName: "Region",
            groupingShowAggregationMenu: false,
            width: 280
         },
         {
            field: "country",
            displayName: "Country",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "itemType",
            displayName: "Item Type",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "salesChannel",
            displayName: "Sales Channel",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "orderPriority",
            displayName: "Order Priority",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "orderDate",
            displayName: "Order Date",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "orderId",
            displayName: "Order ID",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "shipDate",
            displayName: "Ship Date",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "unitsSold",
            displayName: "Units Sold",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "unitPrice",
            displayName: "Unit Price",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "unitCost",
            displayName: "Unit Cost",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "totalRevenue",
            displayName: "Total Revenue",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "totalCost",
            displayName: "Total Cost",
            groupingShowAggregationMenu: false,
            width: 280
         }, {
            field: "totalProfit",
            displayName: "Total Profit",
            groupingShowAggregationMenu: false,
            width: 280
         },
         {
            field: "nric",
            displayName: "NRIC",
            groupingShowAggregationMenu: false,
            width: 280
         }]
   };

   vm.importData = function (file)
   {
      MyService.setLoadingPage('', "Processing started. Please wait...");
      file.upload = Upload.upload({
         url: '/ImportFile',
         headers: {
            'Content-Type': 'multipart/form-data'
         },
         data: {
            files: file
         },
         method: 'POST'
      });

      file.upload.then(function (response)
      {
         MyService.setLoadingPage('none', "");
         vm.gridOptions.data = response.data.orders;
         vm.gridOptions.totalItems = response.data.totalCount;
         vm.isDataPopulated = true;

      });
   }


   vm.gridOptions.onRegisterApi = function (gridApi)
   {
      vm.gridApi = gridApi;

      vm.gridApi.pagination.on.paginationChanged(vm, function (pageNumber, pageSize)
      {
         getNewData(pageNumber, pageSize);
      });
   }

   function getNewData(pageNumber, pageSize)
   {
      MyService.setLoadingPage('', "Fetching orders...");
      $http(
              {
                 method: 'POST',
                 url: '/getOrders',
                 data:
                         {
                            pageSize: pageSize,
                            pageNumber: pageNumber - 1
                         }
              }).success(function (response)
      {
         vm.gridOptions.data = response;
         vm.gridApi.core.notifyDataChange(uiGridConstants.dataChange.ALL);
         MyService.setLoadingPage('none', "");

      }).error(function ()
      {

      });
   }
});
