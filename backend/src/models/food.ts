import mongoose, { Schema } from "mongoose";

const FoodSchema: Schema = new mongoose.Schema({
    "discontinuedDate": {
      "type": "String"
    },
    "foodComponents": {
      "type": "Array"
    },
    "foodAttributes": {
      "type": "Array"
    },
    "foodPortions": {
      "type": "Array"
    },
    "fdcId": {
      "type": "Number"
    },
    "description": {
      "type": "String"
    },
    "publicationDate": {
      "type": "Date"
    },
    "foodNutrients": {
      "type": [
        "Mixed"
      ]
    },
    "dataType": {
      "type": "String"
    },
    "foodClass": {
      "type": "String"
    },
    "modifiedDate": {
      "type": "Date"
    },
    "availableDate": {
      "type": "Date"
    },
    "brandOwner": {
      "type": "String"
    },
    "dataSource": {
      "type": "String"
    },
    "brandedFoodCategory": {
      "type": "String"
    },
    "gtinUpc": {
      "type": "String"
    },
    "householdServingFullText": {
      "type": "String"
    },
    "ingredients": {
      "type": "String"
    },
    "marketCountry": {
      "type": "String"
    },
    "servingSize": {
      "type": "Number"
    },
    "servingSizeUnit": {
      "type": "String"
    },
    "foodUpdateLog": {
      "type": [
        "Mixed"
      ]
    },
    "labelNutrients": {
      "fat": {
        "value": {
          "type": "Number"
        }
      },
      "saturatedFat": {
        "value": {
          "type": "Number"
        }
      },
      "transFat": {
        "value": {
          "type": "Number"
        }
      },
      "cholesterol": {
        "value": {
          "type": "Number"
        }
      },
      "sodium": {
        "value": {
          "type": "Number"
        }
      },
      "carbohydrates": {
        "value": {
          "type": "Number"
        }
      },
      "fiber": {
        "value": {
          "type": "Number"
        }
      },
      "sugars": {
        "value": {
          "type": "Number"
        }
      },
      "protein": {
        "value": {
          "type": "Number"
        }
      },
      "calcium": {
        "value": {
          "type": "Number"
        }
      },
      "iron": {
        "value": {
          "type": "Number"
        }
      },
      "calories": {
        "value": {
          "type": "Number"
        }
      }
    }
  });

  export const Food = mongoose.model("Food",FoodSchema);