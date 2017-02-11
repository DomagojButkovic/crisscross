/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * crisscross module
 */
define(['ojs/ojcore', 'knockout','ojs/ojinputtext'
], function (oj, ko) {
    /**
     * The view model for the main content view template
     */
    function crisscrossContentViewModel() {
        var self = this;

        self.playerName = ko.observable("Player");
        self.statsValue = ko.observable("Stats Area");
        self.gameId ="-1";

        
        self.refreshGrid = function (data, event) {
            $.getJSON( "/game/status?gameId="+self.gameId,
                                function(data)
                                {
                                    console.log( "success" );
                                    var arr= data.game;
                                    self.statsValue(data.status+ " "+data.gameMode) ;
                                    for (var i = 0; i < arr.length; i++){
                                        var obj = arr[i];
                                        $( "#c"+obj['row']+obj['column']).ojButton( "option", "label", obj['value'] );
                                    }

                                });


                    return true;
        }

        self.buttonGridClick = function (data, event) {
            if(self.gameId=="-1"){
              return;
            }

            var  id =  event.currentTarget.id;
            var row =  id.substring(1, 2);
            var column =  id.substring(2, 3);
            $.getJSON( "/game/play?gameId="+self.gameId+"&row="+row+"&column="+column,
                                function(data)
                                {
                                    console.log( "success" );
                                    self.refreshGrid(data,event);

                                });


            return true;
        }


        self.buttonStartGameClick = function (data, event) {
            $.getJSON( "/game/new?player1="+self.playerName(),
                        function(data)
                        {
                            console.log( "success" );
                            self.gameId = data.gameId;
                            self.statsValue(self.gameId+ " "+data.gameMode) ;
                            self.refreshGrid(data,event);
                        });


            return true;
        }
        
       self.buttonStatsClick = function (data, event) {
            $.getJSON( "/game/stats",
                                function(data)
                                {
                                    console.log( "success" );
                                    self.statsValue(JSON.stringify(data));

                                });


                    return true;
        }
    }

    return crisscrossContentViewModel;
});
